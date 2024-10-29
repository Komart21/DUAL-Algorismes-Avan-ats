#!/usr/bin/env python3

import os
import json
import torch
import torch.nn as nn
from transformers import BertTokenizer
from ai_utils_text import ModelConfig, ModelClassifier, getDevice

CONFIG_FILE = "model_config.json"

def clearScreen():
    if os.name == 'nt':  # Si estás en Windows
        os.system('cls')
    else:                # Si estás en Linux o macOS
        os.system('clear')

def predict_text(text: str, model: nn.Module, tokenizer, device: torch.device, config: ModelConfig):
    model.eval()  # Poner el modelo en modo de evaluación
    encoding = tokenizer(
        text,
        add_special_tokens=True,
        max_length=config.max_len,
        padding='max_length',
        truncation=True,
        return_tensors='pt'
    )

    input_ids = encoding['input_ids'].to(device)
    attention_mask = encoding['attention_mask'].to(device)

    with torch.no_grad():
        outputs = model(input_ids, attention_mask)
        probabilities = nn.functional.softmax(outputs, dim=1)
        confidence, predicted = torch.max(probabilities, 1)

    predicted_label = predicted.item()
    confidence = confidence.item()
    return predicted_label, confidence

def main():
    clearScreen()

    # Cargar la configuración
    with open(CONFIG_FILE) as f:
        config_file = json.load(f)

    # Cargar metadatos (se generan durante el entrenamiento)
    with open(config_file['paths']['metadata'], 'r') as f:
        metadata = json.load(f)

    # Configuración del modelo
    config = ModelConfig(config_file, metadata["categories"])

    # Inicializar tokenizer
    tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')

    # Cargar el modelo entrenado
    device = getDevice()
    model = ModelClassifier(config).to(device)
    model.load_state_dict(torch.load(config_file['paths']['trained_network'], map_location=device))
    model.eval()

    # Solicitar opinión del usuario
    user_input = input("What's your opinion about the airline? ")
    
    # Realizar la predicción
    predicted_label, confidence = predict_text(user_input, model, tokenizer, device, config)

    # Mapeo de índice a etiqueta
    label_mapping = {0: 'negative', 1: 'neutral', 2: 'positive'}
    
    # Mostrar el resultado
    print(f"Your opinion about the airline is '{label_mapping[predicted_label]}' with a confidence of {confidence:.2%}")

if __name__ == "__main__":
    main()

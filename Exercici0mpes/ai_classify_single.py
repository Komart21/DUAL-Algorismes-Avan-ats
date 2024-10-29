#!/usr/bin/env python3

import os
import json
import torch
import torch.nn as nn
from transformers import BertTokenizer
from ai_utils_text import ModelConfig, ModelClassifier, getDevice

CONFIG_FILE = "model_config.json"

def clearScreen():
    if os.name == 'nt':     # Windows
        os.system('cls')
    else:                   # Linux or macOS
        os.system('clear')

clearScreen()

def predict_text(text: str, model: nn.Module, tokenizer, device: torch.device, config: ModelConfig):
    model.eval()
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

    return predicted.item(), confidence.item()

def main():
    with open(CONFIG_FILE) as f:
        config_file = json.load(f)

    config = ModelConfig(config_file, ['eng', 'other'])
    tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')

    device = getDevice()
    model = ModelClassifier(config).to(device)
    model.load_state_dict(torch.load(config_file['paths']['trained_network'], map_location=device, weights_only=True))

    text = input("Write something: ")
    predicted_label, confidence = predict_text(text, model, tokenizer, device, config)

    if predicted_label == 1:  # 0 represents 'eng'
        print("This is English")
    else:
        print("I don't understand you")

if __name__ == "__main__":
    main()

import json
import xlsxwriter

# Leer el archivo JSON con las notas
with open('./notes.json', 'r') as file:
    data = json.load(file)

filename = 'notas.xlsx'
workbook = xlsxwriter.Workbook(filename)

# Formatos
bold = workbook.add_format({'bold': True})
centered = workbook.add_format({'align': 'center', 'valign': 'vcenter'})
right_aligned = workbook.add_format({'align': 'right', 'valign': 'vcenter'})
red_text = workbook.add_format({'font_color': '#FF0000'})
green_text = workbook.add_format({'font_color': '#00AA00'})
bold_total = workbook.add_format({'bold': True, 'align': 'left'})
valid_format = workbook.add_format({'bg_color': '#FF9999', 'align': 'center'})
invalid_format = workbook.add_format({'bg_color': '#99FF99', 'align': 'center'})

worksheet1 = workbook.add_worksheet("Notas Normales")
worksheet2 = workbook.add_worksheet("Notas Anónimas")

# Títulos de las columnas y el porcentaje de cada actividad (convertidos a valores sobre 10)
activities = ["Faltas", "PR01", "PR02", "PR03", "PR04", "EX01"]
percentages = [0, 0.1, 0.1, 0.1, 0.2, 0.5]  # Los porcentajes ahora son relativos a una escala de 10

# Encabezados de ambas hojas
headers = activities + ["Vàlid", "Nota Final"]
worksheet1.write_row(0, 0, ["Nom"] + headers, bold)
worksheet2.write_row(0, 0, ["ID"] + headers, bold)

# Corregir los porcentajes: los porcentajes se asignan solo debajo de las actividades calificadas.
worksheet1.write_row(1, 1, percentages, centered)
worksheet2.write_row(1, 1, percentages, centered)

# Añadir datos a ambas hojas
row = 2
for student in data:
    # Primera hoja con nombres
    worksheet1.write(row, 0, student["Name"])  # Nombre del estudiante
    worksheet1.write(row, 1, student.get("%Faltes", 0))  
    worksheet1.write_row(row, 2, [student.get(activity, 0) for activity in activities[1:]])

    # Segunda hoja con IDs anónimos
    worksheet2.write(row, 0, student["id"][2:6])  
    worksheet2.write(row, 1, student.get("%Faltes", 0))  
    worksheet2.write_row(row, 2, [student.get(activity, 0) for activity in activities[1:]])

    # Fórmula para comprobar si es válido (más de 20 faltas o menos de 4 en EX01 no es válido)
    valid_formula = f'=IF(AND(B{row+1}<=20, F{row+1}>=4), "Sí", "No")'
    worksheet1.write_formula(row, len(activities) + 1, valid_formula, centered)
    worksheet2.write_formula(row, len(activities) + 1, valid_formula, centered)

    # Fórmula para la nota final (si no es válido, será 1; si es válido, calculamos la nota ponderada)
    # Usamos MIN y MAX para asegurar que la nota esté entre 1 y 10
    final_grade_formula = f'=IF(G{row+1}="No", 1, SUMPRODUCT(C{row+1}:F{row+1}, $C$2:$F$2))'
    worksheet1.write_formula(row, len(activities) + 2, final_grade_formula, right_aligned)
    worksheet2.write_formula(row, len(activities) + 2, final_grade_formula, right_aligned)

    row += 1

# Añadir formato condicional para resaltar notas menores de 5 en las actividades
for ws in [worksheet1, worksheet2]:
    ws.conditional_format(f'C3:F{row}', {
        'type': 'cell',
        'criteria': '<',
        'value': 2.5,
        'format': red_text
    })

# Añadir formato condicional para resaltar las notas finales
for ws in [worksheet1, worksheet2]:
    ws.conditional_format(f'I3:I{row}', {
        'type': 'cell',
        'criteria': '<',
        'value': 2.5,
        'format': workbook.add_format({'bg_color': '#FF0000', 'font_color': '#FFFFFF', 'align': 'right'})
    })
    ws.conditional_format(f'I3:I{row}', {
        'type': 'cell',
        'criteria': '>=',
        'value': 3.5,
        'format': workbook.add_format({'bg_color': '#00FF00', 'font_color': '#000000', 'align': 'right'})
    })

workbook.close()

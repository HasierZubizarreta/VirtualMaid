import numpy as np
import matplotlib.pyplot as plt
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas

# Datos de ejemplo para el histograma
meses = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
valores = np.random.randint(1, 100, size=12)

# Crear figura y eje
fig, ax = plt.subplots()

# Crear el histograma con las barras
ax.bar(meses, valores)

# Agregar etiquetas y título
ax.set_xlabel('Mes')
ax.set_ylabel('Valor')
ax.set_title('Histograma de valores por mes')

# Guardar la figura como PDF utilizando ReportLab
pdf = canvas.Canvas('histograma.pdf', pagesize=letter)
fig.savefig('histograma.png', dpi=300)
pdf.drawImage('histograma.png', 50, 500, width=500, height=300)
pdf.save()

# Mostrar el histograma en la pantalla
plt.show()


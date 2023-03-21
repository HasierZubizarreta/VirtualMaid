import urllib.request as ul
from bs4 import BeautifulSoup as soup

url = 'https://tarifaluzhora.es/'
req = ul.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
client = ul.urlopen(req)
htmldata = client.read()
client.close()

pagesoup = soup(htmldata, "html.parser")
itemlocator = pagesoup.findAll('div', {"class":"col-xs-9"})

# print(itemlocator)

filename = "precioLuz.csv"
f = open(filename, "w", encoding="utf-8")
headers = "HORA, PRECIO, COLOR\n"
f.write(headers)

for items in itemlocator:   
    # print(items)
    # print()
    
    namecontainer = items.findAll("span",{"itemprop":"description"})
    names = namecontainer[0].text

    pricecontainer = items.findAll("span",{"itemprop":"price"})
    prices = pricecontainer[0].text.strip()
    prices=str(prices).split()

    color = str(items)[36:42]

    if color=="0faf10":
        color="V"
    elif color=="f4a321":
        color="N"
    else:
        color="R"
    
    # print("Hora: "+ names[0:2])
    # print("Precio: " + prices[0]) 
    # print("Color: " + color)   

    f.write(names[0:2] + "," + prices[0] + "," + color + "\n")
f.close()
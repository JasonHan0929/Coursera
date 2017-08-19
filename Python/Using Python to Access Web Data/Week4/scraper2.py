from bs4 import BeautifulSoup
from urllib.request import urlopen

url = input('Enter URL: ').strip()
count = int(input('Enter count: ').strip())
position = int(input('Enter position: ').strip()) - 1

names = []

html = urlopen(url)
bsObj = BeautifulSoup(html, 'lxml')
while True:
	tagObj = bsObj.findAll('a')[position]
	print('Retrieving: ' + url)
	count -= 1
	if count >= 0:
		url = tagObj.get('href');
		bsObj = BeautifulSoup(urlopen(url), 'lxml')
		names.append(tagObj.get_text())
	else:
		break;
print(names[len(names) - 1])

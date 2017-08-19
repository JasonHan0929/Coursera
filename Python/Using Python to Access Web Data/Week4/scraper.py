from bs4 import BeautifulSoup
from urllib.request import urlopen
html = urlopen('http://python-data.dr-chuck.net/comments_313567.html')
bsObj = BeautifulSoup(html)
tags = bsObj.findAll('span', {'class' : 'comments'})
print(sum([int(tag.get_text()) for tag in tags]))
from urllib.request import urlopen  
import xml.etree.ElementTree as ET

url = input('Enter location: ').strip()
if len(url) < 1: exit()
print('Retrieving ' + url)
content = urlopen(url).read()
print('Retrieved %d characters' % len(content))
tree = ET.fromstring(content)
result = tree.findall('./comments/comment')
print('count: %d' % len(result))
print('Sum: %d' % sum([int(str.find('count').text) for str in result]))

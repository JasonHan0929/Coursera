from urllib.request import urlopen
import json

url = input('Enter location: ').strip()
print('Retrieving %s' % url)
data = urlopen(url).read().decode('utf-8') #must use decode() before json.loads()
print('Retrieved %d characters' % len(data))
result = json.loads(data)["comments"]
print('count: %d' % len(result))
print('Sum: %d' % sum([int(obj['count']) for obj in result]))
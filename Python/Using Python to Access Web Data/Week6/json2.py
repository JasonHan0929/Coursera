from urllib.request import urlopen
from urllib.parse import urlencode
import json

place = input('Enter location: ').strip()
url = 'http://python-data.dr-chuck.net/geojson?' + urlencode({'sensor': False, 'address': place})
print('Retrieving', url)
data = urlopen(url).read().decode('UTF-8')
print('Retrieving %d characters' % len(data))
print('Place id %s' % json.loads(data)['results'][0]['place_id'])


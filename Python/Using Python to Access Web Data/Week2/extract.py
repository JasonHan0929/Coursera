import re
result = sum([int(str) for str in re.findall('[0-9]+', open('regex_sum_313562.txt').read())])
print(result)
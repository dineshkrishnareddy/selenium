def powerset(seq):
    if len(seq) <= 1:
        yield seq
        yield []
    else:
        for item in powerset(seq[1:]):
          yield [seq[0]]+item
          yield item

tests = (int)(raw_input(''))
type(tests)
while(tests):
    sum = 0
    inputs = (int)(raw_input(''))
    lists = []
    for i in range(1,inputs+1):
        lists.append(i)
    for x in powerset(lists):
        for num in x:
            sum = sum + num
    print sum
    tests = tests - 1
from pypinyin import pinyin, Style
file = open('./webdict_with_freq.txt', encoding="utf8");
lines = file.readlines();
wordsDict = {};
for line in lines:
  if len(line.split(" ")[0]) < 2 or len(line.split(" ")[0]) > 4:
    continue
  wordsDict[line.split(" ")[0]] = float(line.split(" ")[1].strip())
wordsArray = sorted(wordsDict.items(), key=lambda x: x[1], reverse=True)[:1000];
outputArray = [];
for wordIndex, word in enumerate(wordsArray):
  pinyinArray = pinyin(word[0], style=Style.NORMAL);
  for ziIndex, zi in enumerate(list(word[0])):
    outputArray.append(
      "{},{},{},{},{}".format(wordIndex+1, word[0], ziIndex, pinyinArray[ziIndex][0], zi));
with open("zhWords.csv", "w", encoding="utf8") as txt_file:
  txt_file.write("zhWords_id,zhWords_word,ziIndex,pinyin,zi\n");
  for line in outputArray:
      txt_file.write(line+"\n");
print("generated 1000 Ci to zhWords.csv");
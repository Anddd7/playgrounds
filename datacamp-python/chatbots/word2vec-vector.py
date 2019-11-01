import spacy
import numpy as np

nlp = spacy.load("en_core_web_lg")
print(nlp("cat").vector)
print(nlp("dog").vector)
print(nlp("can").vector)

print(nlp("cat").similarity(nlp("dog")))
print(nlp("cat").similarity(nlp("can")))

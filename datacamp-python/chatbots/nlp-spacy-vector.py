import spacy
import numpy as np

smnlp = spacy.load("en_core_web_sm")
mdnlp = spacy.load("en_core_web_md")
# lgnlp = spacy.load("en_core_web_lg")

# print(smnlp("cat").vector)
# print(smnlp("dog").vector)
# print(smnlp("can").vector)

# print(smnlp("cat").similarity(smnlp("dog")))
# print(smnlp("cat").similarity(smnlp("can")))

print("smnlp.vocab.vectors: ", len(smnlp.vocab.vectors))

tokens = smnlp("dog cat banana afskfsd")
for token in tokens:
    print(
        token.text,
        token.has_vector,
        token.vector_norm,
        token.is_oov,
    )

print("--------------------------------")

print("mdnlp.vocab.vectors: ", len(mdnlp.vocab.vectors))
# tokens = mdnlp("dog cat banana afskfsd")
# for token in tokens:
#     print(
#         token.text,
#         token.has_vector,
#         token.vector_norm,
#         token.is_oov,
#     )

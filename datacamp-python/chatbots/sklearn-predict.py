from sklearn import svm
import spacy
import numpy as np

# basic case
# X = [[0, 0], [1, 1], [2, 2]]
# y = [0, 1, 2]
# clf = svm.SVC(gamma='scale')
# clf.fit(X, y)

# print(
#     clf.predict([[0.4999999999, 0.499999999999]])
# )
# print(
#     clf.predict([[1.31222241, 1.5]])
# )
# print(
#     clf.predict([[2.5, 2.5]])
# )

nlp = spacy.load("en_core_web_md")
n_vectors = nlp.vocab.vectors_length  # default 300 in spacy

# 准备训练数据
sentences = [
    "Hello, nice to meet you",
    "How's weather today?",
    "What are you doing?",
]
X_trains = np.zeros((len(sentences), n_vectors))
y_trains = ["greet", "ask_weather", "ask_job"]

for index, sentence in enumerate(sentences):
    X_trains[index] = nlp(sentence).vector

# 建立模型
clf = svm.SVC(C=10, gamma='auto')
clf.fit(X_trains, y_trains)

# 校验测试数据
test_sentences = [
    "Hello, nice to meet you",
    "How's weather today?",
    "What are you doing?",
    #
    "Hello",
    "What's your job",
    "What's the weather today?",
    "Nice to see you",
    "Welcome, glad to see you",
]
X_tests = np.zeros((len(test_sentences), n_vectors))

for index, sentence in enumerate(test_sentences):
    X_tests[index] = nlp(sentence).vector

print(clf.predict(X_tests))

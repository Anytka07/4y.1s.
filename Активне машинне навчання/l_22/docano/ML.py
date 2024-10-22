import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LogisticRegression

class YourMLModel:
    def __init__(self):
        self.vectorizer = CountVectorizer()  # Векторизатор для перетворення тексту в числовий формат
        self.model = LogisticRegression()  # Класифікатор

    def train(self, labeled_data):
        # Розділити дані на текст та мітки
        texts = [item['text'] for item in labeled_data]
        labels = [item['label'] for item in labeled_data]

        # Векторизація тексту
        X = self.vectorizer.fit_transform(texts)
        y = np.array(labels)

        # Навчання моделі
        self.model.fit(X, y)
        return self

    def predict(self, unlabeled_data):
        # Векторизація нерозмічених даних
        X_unlabeled = self.vectorizer.transform([item['text'] for item in unlabeled_data])  # Заміни 'text' на правильний ключ

        # Прогнозування міток
        return self.model.predict(X_unlabeled)

    def predict_proba(self, unlabeled_data):
        # Векторизація нерозмічених даних
        X_unlabeled = self.vectorizer.transform([item['text'] for item in unlabeled_data])  # Заміни 'text' на правильний ключ

        # Прогнозування ймовірностей
        return self.model.predict_proba(X_unlabeled)

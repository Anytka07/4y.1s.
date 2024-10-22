import numpy as np


class ActiveLearningAlgorithm:
    def select(self, model, unlabeled_data, num_examples=5):
        # Отримання ймовірностей для нерозмічених даних
        probabilities = model.predict_proba(unlabeled_data)

        # Визначення невпевнених прикладів
        uncertainty = 1 - np.max(probabilities, axis=1)  # Обчислюємо невпевненість
        uncertain_indices = np.argsort(uncertainty)[-num_examples:]  # Отримуємо індекси найбільш невпевнених прикладів

        # Повертаємо вибрані приклади
        selected_examples = [unlabeled_data[i] for i in uncertain_indices]
        return selected_examples

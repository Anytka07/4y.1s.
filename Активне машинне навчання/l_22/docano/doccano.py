import doccano_client  # Бібліотека для взаємодії з Doccano
import ML  # Модель машинного навчання
import Active  # Алгоритм активного навчання

# Ініціалізація клієнта Doccano
client = doccano_client.DoccanoClient(
    url='http://localhost:8000',  # URL до Doccano
    username='Anna',  # Твоє ім'я користувача
    password='12345'  # Твій пароль
)

# Налаштування параметрів
project_id = 1  # ID проекту в Doccano
num_iterations = 5  # Кількість ітерацій

# Цикл активного навчання
for iteration in range(num_iterations):
    print(f"Iteration {iteration + 1}...")

    # Отримання розмічених даних
    labeled_data = client.export_dataset(project_id)

    # Навчання моделі
    model = ML.train(labeled_data)

    # Отримання нерозмічених даних
    unlabeled_data = client.get_unlabeled_data(project_id)

    # Вибір нових прикладів для розмітки
    selected_examples = Active.select(model, unlabeled_data)

    # Імпорт вибраних прикладів назад у Doccano
    client.import_dataset(project_id, selected_examples)

    print(f"Completed iteration {iteration + 1}")

print("Active learning process completed.")
# Отримання списку проектів
projects = client.get_projects()
print("Список проектів:", projects)

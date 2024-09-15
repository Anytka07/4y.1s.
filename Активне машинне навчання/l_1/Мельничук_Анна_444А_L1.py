import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.datasets import fashion_mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.optimizers import SGD
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score

# Завантаження даних Fashion MNIST
"""
  Дані Fashion MNIST завантажуються з бібліотеки tensorflow.keras.datasets.
  Ця бібліотека надає вбудовану функцію fashion_mnist.load_data(), яка завантажує набір даних з інтернету (з серверів TensorFlow)
"""
(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()

# Вибір 5 класів (футболка, брюки, светр, плаття, пальто)
selected_classes = [0, 1, 2, 3, 4]

# Фільтрація наборів даних для використання лише обраних класів
train_filter = np.isin(y_train, selected_classes)
test_filter = np.isin(y_test, selected_classes)

x_train, y_train = x_train[train_filter], y_train[train_filter]
x_test, y_test = x_test[test_filter], y_test[test_filter]

# Перевизначення міток (0, 1, 2, 3, 4)
# Перетворюємо мітки класів на послідовні значення від 0 до 4 для обраних 5 класів
y_train = np.array([np.where(selected_classes == y)[0][0] for y in y_train])
y_test = np.array([np.where(selected_classes == y)[0][0] for y in y_test])

# Підготовка даних для моделі (перетворення зображень на вектори та нормалізація)
x_train = x_train.reshape(x_train.shape[0], 784).astype('float32') / 255
x_test = x_test.reshape(x_test.shape[0], 784).astype('float32') / 255

# Перетворення міток в категоричний формат ( формат категорій (one-hot encoding) для 5 класів)
y_train = to_categorical(y_train, 5)
y_test = to_categorical(y_test, 5)

# Функція для побудови моделі
"""
 Ця функція створює і компілює нейронну мережу для класифікації, що складається з двох прихованих шарів
 із функцією активації ReLU та вихідного шару з 5 класами з функцією активації softmax. 
 Модель використовує стохастичний градієнтний спуск (SGD) та categorical crossentropy як функцію втрат.
"""
def build_model():
    model = Sequential()
    model.add(Dense(1000, input_dim=784, activation="relu"))
    model.add(Dense(300, activation="relu"))
    model.add(Dense(5, activation="softmax"))  # Вихідний шар для 5 класів
    model.compile(loss="categorical_crossentropy", optimizer=SGD(), metrics=["accuracy"])
    return model

# Функція для розрахунку метрик

"""
 Ця функція обчислює основні метрики продуктивності моделі 
 (точність, precision, recall та F1-score) на тестовому наборі даних, 
 що допомагає оцінити якість її класифікації.

"""
def evaluate_model(model, x_test, y_test):
    y_pred = model.predict(x_test)
    y_test_labels = np.argmax(y_test, axis=1)
    y_pred_labels = np.argmax(y_pred, axis=1)

    # Обчислення метрик точності, precision, recall та F1-score
    accuracy = accuracy_score(y_test_labels, y_pred_labels)
    precision = precision_score(y_test_labels, y_pred_labels, average='macro')
    recall = recall_score(y_test_labels, y_pred_labels, average='macro')
    f1 = f1_score(y_test_labels, y_pred_labels, average='macro')

    return accuracy, precision, recall, f1

# Функція для реалізації пасивного навчання на повному наборі даних.
"""
  Ця функція реалізує процес пасивного навчання моделі нейронної мережі на повному 
  навчальному наборі даних, тренуючи її протягом 20 епох та оцінюючи продуктивність за 
  допомогою метрик точності, precision, recall та F1-score після кожної епохи.

"""

def passive_learning():
    # Створюємо модель з архітектурою, визначеною в функції build_model.
    model = build_model()
    
    # Ініціалізуємо словник для збереження історії метрик точності, precision, recall і F1-score.
    history = {'accuracy': [], 'precision': [], 'recall': [], 'f1': []}

    # Тренуємо модель протягом 20 епох.
    for epoch in range(1, 21):
        # Навчання моделі на всьому тренувальному наборі з використанням пакетного навчання та валідації.
        model.fit(x_train, y_train, batch_size=100, epochs=1, validation_split=0.2, verbose=1)
        
        # Оцінюємо продуктивність моделі на тестовому наборі та отримуємо метрики.
        accuracy, precision, recall, f1 = evaluate_model(model, x_test, y_test)

        # Зберігаємо значення метрик після кожної епохи в словнику 'history'.
        history['accuracy'].append(accuracy)
        history['precision'].append(precision)
        history['recall'].append(recall)
        history['f1'].append(f1)

        # Виводимо значення метрик для поточної епохи.
        print(f"Пасивне навчання (Епоха {epoch}) - Точність: {accuracy*100:.2f}%, Precision: {precision*100:.2f}%, Recall: {recall*100:.2f}%, F1-score: {f1*100:.2f}%")
    
    # Повертаємо навчану модель та історію метрик для подальшого аналізу.
    return model, history


# Функція для реалізації активного навчання з певним бюджетом на розмітку та початковою підмножиною даних.
"""
  Ця функція реалізує процес активного навчання, додаючи до навчального набору зразки з 
  найвищою невизначеністю до досягнення цільової точності або використання всього бюджету на розмітку.

"""
def active_learning(budget=6000, initial_size=3000, query_size=500, target_accuracy=0.90):
    # Вибір початкової підмножини даних для навчання.
    x_initial = x_train[:initial_size]
    y_initial = y_train[:initial_size]

    # Формування пулу нерозмічених даних.
    x_pool = x_train[initial_size:]
    y_pool = y_train[initial_size:]

    # Створення моделі з архітектурою, визначеною в функції build_model.
    model = build_model()
    
    # Ініціалізація словника для збереження історії метрик точності, precision, recall і F1-score.
    history_active = {'accuracy': [], 'precision': [], 'recall': [], 'f1': []}

    # Цикл активного навчання, що триває до досягнення цільової точності або використання всього бюджету.
    while len(x_initial) < initial_size + budget:
        # Навчання моделі на поточному наборі розмічених даних.
        model.fit(x_initial, y_initial, epochs=10, batch_size=100, verbose=1)
        
        # Оцінка продуктивності моделі на тестовому наборі та збереження метрик.
        accuracy, precision, recall, f1 = evaluate_model(model, x_test, y_test)
        history_active['accuracy'].append(accuracy)
        history_active['precision'].append(precision)
        history_active['recall'].append(recall)
        history_active['f1'].append(f1)
        
        # Виведення значень метрик для поточної ітерації.
        print(f"Активне навчання - Точність: {accuracy*100:.2f}%, Precision: {precision*100:.2f}%, Recall: {recall*100:.2f}%, F1-score: {f1*100:.2f}%")

        # Перевірка, чи досягнута цільова точність.
        if accuracy >= target_accuracy:
            print(f"Досягнута цільова точність: {accuracy*100:.2f}%")
            break

        # Оцінка невизначеності та вибір зразків для розмітки.
        predictions = model.predict(x_pool)
        uncertainties = 1 - np.max(predictions, axis=1)
        
        # Вибір зразків з найвищою невизначеністю для додавання до навчального набору.
        query_indices = np.argsort(uncertainties)[-query_size:]
        x_new = x_pool[query_indices]
        y_new = y_pool[query_indices]

        # Оновлення навчального набору новими розміченими зразками.
        x_initial = np.concatenate((x_initial, x_new), axis=0)
        y_initial = np.concatenate((y_initial, y_new), axis=0)

        # Видалення вибраних зразків з пулу нерозмічених даних.
        x_pool = np.delete(x_pool, query_indices, axis=0)
        y_pool = np.delete(y_pool, query_indices, axis=0)

    # Повертаємо навчану модель та історію метрик для подальшого аналізу.
    return model, history_active


# Функція для побудови графіків, що порівнюють продуктивність моделей, навчених за допомогою пасивного та активного навчання.
def plot_comparison(history_passive, history_active):
    # Визначення кількості епох для кожної моделі на основі довжини історії метрик.
    epochs_passive = range(1, len(history_passive['accuracy']) + 1)
    epochs_active = range(1, len(history_active['accuracy']) + 1)

    # Налаштування розмірів фігури для графіків.
    plt.figure(figsize=(12, 8))

    # Графік точності (accuracy) для пасивного та активного навчання.
    plt.subplot(2, 2, 1)
    plt.plot(epochs_passive, history_passive['accuracy'], 'bo-', label='Пасивне навчання')
    plt.plot(epochs_active, history_active['accuracy'], 'ro-', label='Активне навчання')
    plt.title('Точність')
    plt.xlabel('Епохи')
    plt.ylabel('Точність')
    plt.legend()

    # Графік precision для пасивного та активного навчання.
    plt.subplot(2, 2, 2)
    plt.plot(epochs_passive, history_passive['precision'], 'bo-', label='Пасивне навчання')
    plt.plot(epochs_active, history_active['precision'], 'ro-', label='Активне навчання')
    plt.title('Precision')
    plt.xlabel('Епохи')
    plt.ylabel('Precision')
    plt.legend()

    # Графік recall для пасивного та активного навчання.
    plt.subplot(2, 2, 3)
    plt.plot(epochs_passive, history_passive['recall'], 'bo-', label='Пасивне навчання')
    plt.plot(epochs_active, history_active['recall'], 'ro-', label='Активне навчання')
    plt.title('Recall')
    plt.xlabel('Епохи')
    plt.ylabel('Recall')
    plt.legend()

    # Графік F1-score для пасивного та активного навчання.
    plt.subplot(2, 2, 4)
    plt.plot(epochs_passive, history_passive['f1'], 'bo-', label='Пасивне навчання')
    plt.plot(epochs_active, history_active['f1'], 'ro-', label='Активне навчання')
    plt.title('F1-score')
    plt.xlabel('Епохи')
    plt.ylabel('F1-score')
    plt.legend()

    # Оптимізація розміщення підграфіків на фігурі.
    plt.tight_layout()
    plt.show()


# Виконання пасивного та активного навчання
passive_model, passive_history = passive_learning()
active_model, active_history = active_learning()

# Порівняння продуктивності моделей
plot_comparison(passive_history, active_history)

"""
Цей код демонструє два підходи до навчання нейронної мережі на наборі даних Fashion MNIST:

1. Пасивне навчання : модель тренується на всьому доступному наборі даних протягом 20 епох, 
і результати оцінюються за кількома метриками продуктивності після кожної епохи.

2. Активне навчання : модель починає з початкової підмножини даних і поступово додає нові зразки
 з пулу нерозмічених даних, вибираючи зразки з найбільшою невизначеністю, до досягнення певної 
 цільової точності або використання визначеного бюджету на розмітку.

Остаточні результати порівнюються за допомогою графіків, що показують, як змінилися метрики 
точності, precision, recall і F1-score для обох підходів.
"""

<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Моя PHP-програма: Табулювання функції</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
            margin: 0 auto;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #c82333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 2px solid #ff0000;
        }
        th, td {
            padding: 10px;
            text-align: center;
            color: #ffff00;
            background-color: #800000;
        }
        caption {
            font-size: 20px;
            font-family: "Times New Roman", Times, serif;
            font-weight: bold;
            color: #990000;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .submit-button {
            background-color: yellow;
            font-family: "Times New Roman", Times, serif;
            font-size: 16px;
            font-weight: bold;
            color: black;
            border: 2px solid #000;
            padding: 10px 20px;
            cursor: pointer;
            margin-top: 10px;
            width: 100%;
        }
        .submit-button:hover {
            background-color: blue;
            color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Опис функції -->
    <h2>Табулювання функції</h2>
    <p>Виберіть функцію для табулювання:</p>

    <!-- Форма для введення даних -->
    <form action="" method="POST">
        <label for="function">Функція:</label>
        <select id="function" name="function">
            <option value="1" <?php if (isset($_POST['function']) && $_POST['function'] === "1") echo 'selected'; ?>>y = sqrt(x) / (x + 5)</option>
            <option value="2" <?php if (isset($_POST['function']) && $_POST['function'] === "2") echo 'selected'; ?>>y = sqrt(x) + x^2</option>
            <option value="3" <?php if (isset($_POST['function']) && $_POST['function'] === "3") echo 'selected'; ?>>y = cos(x)^2 + sqrt(x)</option>
        </select>

        <label for="a">Нижня межа:</label>
        <input type="text" id="a" name="a" value="<?php if (isset($_POST['a'])) {echo $_POST['a'];} else {echo '1';} ?>"><br><br>

        <label for="b">Верхня межа:</label>
        <input type="text" id="b" name="b" value="<?php if (isset($_POST['b'])) {echo $_POST['b'];} else {echo '6';} ?>"><br><br>

        <label for="h">Крок табулювання:</label>
        <input type="text" id="h" name="h" value="<?php if (isset($_POST['h'])) {echo $_POST['h'];} else {echo '1';} ?>"><br><br>

        <!-- Стилізована кнопка табулювання -->
        <input type="submit" value="Табулювати" class="submit-button">
    </form>

    <?php
    // Перевіряємо, чи форму було надіслано методом POST
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // Зчитуємо введені значення або задаємо значення за замовчуванням
        $a = isset($_POST['a']) ? $_POST['a'] : 1;
        $b = isset($_POST['b']) ? $_POST['b'] : 6;
        $h = isset($_POST['h']) ? $_POST['h'] : 1;
        $function = $_POST['function'];

        // Перевіряємо, чи входять значення в допустимі межі
        if (!is_numeric($a) || !is_numeric($b) || !is_numeric($h)) {
            echo "<p style='color:red;'>Помилка: межі повинні бути числами.</p>";
        } elseif ($h == 0) {
            echo "<p style='color:red;'>Помилка: крок табулювання не може бути нульовим.</p>";
        } elseif ($a < 0 || $b < 0) {
            echo "<p style='color:red;'>Недопустиме значення: межі не можуть бути від'ємними.</p>";
        } elseif ($a > $b) {
            echo "<p style='color:red;'>Помилка: нижня межа не може бути більшою за верхню.</p>";
        } else {
            // Виводимо таблицю
            echo "<table>";
            echo "<caption>Підрахунок ф-ії</caption>";
            echo "<tr><th>Аргумент</th><th>Результат</th></tr>";

            // Табулювання функції
            $x = $a;
            while ($x <= $b) {
                // Визначаємо функцію на основі вибору
                if ($function === "1") {
                    // Перевіряємо, чи не виникає ділення на нуль
                    if ($x + 5 == 0) {
                        echo "<tr><td>$x</td><td style='color:red;'>Недопустиме значення: ділення на нуль</td></tr>";
                    } else {
                        // Обчислюємо значення функції
                        $y = sqrt($x) / ($x + 5);
                        echo "<tr><td>$x</td><td>$y</td></tr>";
                    }
                } elseif ($function === "2") {
                    // Обчислюємо значення функції
                    $y = sqrt($x) + pow($x, 2);
                    echo "<tr><td>$x</td><td>$y</td></tr>";
                } elseif ($function === "3") {
                    // Обчислюємо значення функції
                    $y = pow(cos($x), 2) + sqrt($x);
                    echo "<tr><td>$x</td><td>$y</td></tr>";
                }
                $x += $h;
            }
            echo "</table>";
        }
    }
    ?>

</div>

</body>
</html>

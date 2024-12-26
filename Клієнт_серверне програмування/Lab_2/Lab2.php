<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Моя PHP-програма</title>
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

        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="radio"], input[type="checkbox"] {
            width: auto;
        }

        input[type="submit"], input[type="button"] {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #c82333;
        }

        .result-block {
            background-color: #ffe9e9;
            padding: 20px;
            margin-top: 20px;
            border: 2px solid #dc3545;
            border-radius: 8px;
            max-width: 400px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .result-block h3 {
            color: #333;
        }

        .result-block p {
            margin: 8px 0;
        }

        .button-group {
            display: flex;
            justify-content: space-between;
        }
        
    </style>
    <script>
        function resetPage() {
            // Перезавантаження сторінки з очищенням форми
            window.location.href = window.location.pathname;
        }
    </script>
</head>
<body>

<?php
$rb = ['','','',''];
if (isset($_POST['rb'])) {
    $rb[$_POST['rb']] = 'checked';
}

$checkboxChecked = '';
if (isset($_POST['checkbox'])) {
    $checkboxChecked = 'checked'; 
}
?>

    <!-- HTML форма для генерування POST-запитів -->
    <form action="" method="POST">
        <!-- Текстове поле для змінної a-->
        <label for="zminna1">Змінна 1 (a):</label>
        <input type="text" id="zminna1" name="zminna1" value="<?= $_POST['zminna1'] ?? '' ?>"><br>

        <!-- Текстове поле для змінної b-->
        <label for="zminna2">Змінна 2 (b):</label>
        <input type="text" id="zminna2" name="zminna2" value="<?= $_POST['zminna2'] ?? '' ?>"><br>

        <!-- Текстове поле для змінної c -->
        <label for="zminna3">Змінна 3 (c):</label>
        <input type="text" id="zminna3" name="zminna3" value="<?= $_POST['zminna3'] ?? '' ?>"><br>

        <!-- Текстова область -->
        <label for="textarea">Текстова область:</label>
        <textarea id="textarea" name="textarea" rows="4" cols="30"><?= $_POST['textarea'] ?? 'Напиши мені тут віршика' ?></textarea><br>

        <!-- Радіокнопки -->
        <label>Виберіть улюблену тваринку :</label><br>
        <input type="radio" id="option1" name="rb" value="1" <?= $rb[1] ?>>
        <label for="option1">киця</label><br>
        <input type="radio" id="option2" name="rb" value="2" <?= $rb[2] ?>>
        <label for="option2">песик</label><br>
        <input type="radio" id="option3" name="rb" value="3" <?= $rb[3] ?>>
        <label for="option3">свинка</label><br><br>

        <!-- Прапорець -->
        <label for="checkbox">Виберіть якщо ви українець <img style="vertical-align: middle;" src="img/ggg.png" alt="Map"></label>
        <input type="checkbox" id="checkbox" name="checkbox" value="Поставлений - Справжній козак!" <?= $checkboxChecked ?>><br><br>

        <!-- Список -->
        <label for="dropdown">Виберіть свою стать:</label>
        <select id="dropdown" name="dropdown">
            <option value="Чоловік" <?= isset($_POST['dropdown']) && $_POST['dropdown'] == 'Чоловік' ? 'selected' : '' ?>>Чоловік</option>
            <option value="Жінка" <?= isset($_POST['dropdown']) && $_POST['dropdown'] == 'Жінка' ? 'selected' : '' ?>>Жінка</option>
            <option value="Я незнаю шо я таке" <?= isset($_POST['dropdown']) && $_POST['dropdown'] == 'Я незнаю шо я таке' ? 'selected' : '' ?>>Я незнаю шо я таке</option>
        </select><br><br>

        
        <!-- Група кнопок -->
        <div class="button-group">
            <!-- Кнопка для відправки форми -->
            <input type="submit" value="Відправити">

            <!-- Кнопка для очищення форми та результатів -->
            <input type="button" value="Очистити все" onclick="resetPage()">
        </div>
    </form>

    <br />

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $a = isset($_POST['zminna1']) ? $_POST['zminna1'] : '';
    $b = isset($_POST['zminna2']) ? $_POST['zminna2'] : '';
    $c = isset($_POST['zminna3']) ? $_POST['zminna3'] : '';
    $textarea = $_POST['textarea'] ?? '';
    $radioOption = isset($_POST['rb']) ? ($_POST['rb'] == '1' ? 'киця' : ($_POST['rb'] == '2' ? 'песик' : 'свинка')) : 'Нічого не вибрано';
    $checkbox = isset($_POST['checkbox']) ? $_POST['checkbox'] : 'Прапорець не вибрано';
    $dropdown = $_POST['dropdown'] ?? '';

    if (!is_numeric($a) || !is_numeric($b) || !is_numeric($c)) {
        echo "<div class='result-block'><h3>Помилка:</h3><p>Будь ласка, впишіть коректні числові значення для змінних a, b і c.</p></div>";
    } else {
        $a = (int)$a;
        $b = (int)$b;
        $c = (int)$c;
        $rez = $a * $b * $c;

        echo "<div class='result-block'>";
        echo "<h3>Результат обрахунку:</h3>";
        echo "<p>a * c * b = $a * $c * $b = $rez</p>";
    
        echo "<h3>Введені значення:</h3>";
        echo "<p>Тут твій віршик: $textarea</p>";
        echo "<p>Вибрана тваринка: $radioOption</p>";
        echo "<p>Прапорець: $checkbox</p>";
        echo "<p>Хто ж я? : $dropdown</p>";
        echo "</div>";
    }
}
?>

</body>
</html>

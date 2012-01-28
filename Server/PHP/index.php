<?php

$version = "1.0";
$min_version = "1.0";
$player_pos_x = 10;
$player_pos_y = 10;

$db_host = "localhost";
$db_user = "root";
$db_password = "Passwort";
$db_datenbank = "gfgame";

$username = "";

$db_link = mysql_connect($db_host, $db_user, $db_password) or die("Anfrage fehlgeschlagen.") . mysql_error();
mysql_select_db($db_datenbank) or die("Die MYSQL-Datenbank konnte nicht ausgew&auml;hlt werden.");

if (isset($_REQUEST['username'])) {
    $username = mysql_escape_string($_REQUEST['username']);
}

if (isset($_REQUEST['option']) && $_REQUEST['option'] == "getAnimals") {
    echo "[Animals]<br>AnimalCounter=2";
    exit;
}

if (isset($_REQUEST['option']) && $_REQUEST['option'] == "getQuest") {

    $userid = 1;

    $sql = "SELECT * FROM `quests` LEFT JOIN `user_quests` ON `quests`.`id` = `user_quests`.`quest_id` WHERE `userid` = '" . $userid . "'; ";
    $db_erg = mysql_query($sql) or die("Anfrage fehlgeschlagen.");
    
    $i = 0;
    
    while ($zeile = mysql_fetch_array($db_erg, MYSQL_ASSOC)) {
        
        if ($i == 0) {
            echo $zeile['Questname'];
            $i++;
        }
        
    }

    exit;

}

if (isset($_REQUEST['option']) && $_REQUEST['option'] == "getAnimalsData") {

header('Content-Disposition: attachment; filename=datei.xml');
header('Content-Type: application/octet-stream'); 

    echo "<?xml version=\"1.0\"?><br>";
    echo "  <content><br>";

    echo "      <min_version>" . $min_version . "</min_version><br>";
    echo "      <version>" . $version . "</version><br>";
    
    $sql = "SELECT * FROM `animals` WHERE `activated` = '1'; ";
    $db_erg = mysql_query($sql) or die("Anfrage fehlgeschlagen." . mysql_error());
    
    while ($zeile = mysql_fetch_array($db_erg, MYSQL_ASSOC)) {
        echo "      <animal animalID=\"" . $zeile['animal_id'] . "\" id=\"" . $zeile['id'] . "\" name=\"" . $zeile['name'] . "\" Animalname=\"" . $zeile['animal_name'] . "\"><br>";
        echo "              <geschlecht>" . $zeile['geschlecht'] . "</geschlecht><br>";
        echo "              <alter>" . $zeile['alter'] . "</alter><br>";
        echo "              <animal_pos_x>" . $zeile['animal_pos_x'] . "</animal_pos_x><br>";
        echo "              <animal_pos_y>" . $zeile['animal_pos_y'] . "</animal_pos_y><br>";
        echo "      </animal><br>";
    }
    
    echo "  </content>";
    
    exit;
}

echo "<?xml version=\"1.0\"?>";
echo "<content>";

echo "<min_version>" . $min_version . "</min_version>";
echo "<version>" . $version . "</version>";
echo "<player_pos_x>" . $player_pos_x . "</player_pos_x>";
echo "<player_pos_y>" . $player_pos_y . "</player_pos_y>";

if (!empty($_REQUEST['username'])) {
    echo "<info>Username ok.</info>";
} else {
    echo "<username>" . $username . "</username>";
}

//echo "<file>" . $_SERVER['PHP_SELF'] . "</file>";

//echo "<anzahl_pns>" . $anzahl_neue_pns . "</anzahl_pns>";
//echo "<eingeloggt>" . $eingeloggt_ . "</eingeloggt>";
//echo "<username>" . $username . "</username>";
//echo "<passwort>" . $passwort . "</passwort>";

echo "</content>";

?>
<?php

$version = "1.0";
$min_version = "1.0";
$player_pos_x = 10;
$player_pos_y = 10;

$username = "";

if (isset($_REQUEST['username'])) {
    $username = mysql_escape_string($_REQUEST['username']);
}

if (isset($_REQUEST['option']) && $_REQUEST['option'] == "getAnimals") {
    echo "[Animals]\r\nAnimalCounter=2";
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
<?php

class User {
    
    //
    
    public function isLevel ($user) {
        return false;
    }
    
    public static function getSections ($password) {
    
        $i = 0;
        $sections[][] = null;
        
        $sql = "SELECT * FROM `passwords` WHERE `password` = '" . mysql_escape_string($password) . "'  AND `activated` = '1'; ";
        $db_erg = mysql_query($sql) or die("Anfrage fehlgeschlagen." . mysql_error());
        
        while ($zeile = mysql_fetch_array($db_erg, MYSQL_ASSOC)) {
            $sections[$i][0] = $zeile['id'];
            $sections[$i][1] = $zeile['bereich'];
            $sections[$i][2] = $zeile['level'];
            $sections[$i][3] = $zeile['webseite'];
        }
        
    }
    
}

?>
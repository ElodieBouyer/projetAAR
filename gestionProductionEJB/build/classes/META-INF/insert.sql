-- 10 +/- SELECT COUNT(*) FROM PUBLIC.ACCESS;  
INSERT INTO ACCESS VALUES (12, 'main', 'R'),(13, 'manufacture', 'RW'),(14, 'product', 'R'),(15, 'product', 'W'),(16, 'product', 'RW'),(17, 'component', 'RW'),(18, 'component', 'W'),(19, 'order', 'RW'),(20, 'order', 'R'),(21, 'users', 'RW'); 
    
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;     
INSERT INTO ROLE VALUES (1, 'Assembleur'),(2, 'Commercial'),(3, 'Service livraison'),(4, 'Service gestion des stocks'),(5, 'Administration'),(6, 'Nill');            

-- 20 +/- SELECT COUNT(*) FROM PUBLIC.ROLE_ACCESS;             
INSERT INTO ROLE_ACCESS VALUES (1, 12),(1, 13),(1, 16),(1, 17),(1, 20),(2, 12),(2, 14),(2, 19),(3, 12),(3, 15),(3, 19),(4, 12),(4, 14),(4, 18),(5, 12),(5, 13),(5, 16),(5, 17),(5, 19),(5, 21);    

-- 5 +/- SELECT COUNT(*) FROM PUBLIC.USER;     
INSERT INTO USER VALUES (7, 'assembleur', 'Assembleur', 'assembleur', 1),(8, 'commercial', 'Commercial', 'commercial', 2),(9, 'livraison', 'Livraison', 'livraison', 3),(10, 'stock', 'Stock', 'stock', 4),(11, 'admin', 'Admin', 'admin', 5);          


-- 12 +/- SELECT COUNT(*) FROM PUBLIC.COMPONENT;               
INSERT INTO COMPONENT VALUES (22, 2, 'Ecran 3.5''''', 5),(23, 2, 'Ecran 5''''', 5),(24, 2, 'Ecran 10''''', 4),(25, 2, 'Ecran 12''''', 5),(26, 2, 'Ecran 20''''', 4),(27, 2, 'Ecran 30''''', 5),(28, 3, 'Ecran 49''''', 1),(29, 5, 'Ecran 70''''', 5),(30, 20, 'Cable HDMI', 100),(31, 20, 'Cable USB', 76),(32, 20, 'Boitier', 50),(33, 5, 'Ecran 80''''', 5); 

-- 12 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCT; 
INSERT INTO PRODUCT VALUES (34, 'Mobile 3.5'''' noir', 5),(35, 'Tablette 10''''', 0),(36, STRINGDECODE('T\u00e9l\u00e9 30'''''), 2),(37, 'Mobile 3.5'''' bleu', 0),(38, 'Mobile 3.5'''' rouge', 0),(39, 'Mobile 3.5'''' blanc', 0),(40, 'Tablette 12'''' blanche', 2),(41, 'Tablette 20''''', 1),(42, 'Tablette 12'''' noire', 1),(43, STRINGDECODE('T\u00e9l\u00e9 49'''''), 1),(44, STRINGDECODE('T\u00e9l\u00e9 70'''''), 1),(45, STRINGDECODE('T\u00e9l\u00e9 80'''''), 0);


-- 36 +/- SELECT COUNT(*) FROM PUBLIC.COMPONENTDETAIL;         
INSERT INTO COMPONENTDETAIL VALUES (1, 34, 22),(1, 34, 31),(1, 34, 32),(1, 35, 24),(1, 35, 31),(1, 35, 32),(1, 36, 27),(1, 36, 30),(1, 36, 32),(1, 37, 22),(1, 37, 31),(1, 37, 32),(1, 38, 22),(1, 38, 31),(1, 38, 32),(1, 39, 22),(1, 39, 31),(1, 39, 32),(1, 40, 25),(1, 40, 31),(1, 40, 32),(1, 41, 26),(1, 41, 31),(1, 41, 32),(1, 42, 23),(1, 42, 31),(1, 42, 32),(1, 43, 28),(1, 43, 30),(1, 43, 32),(1, 44, 29),(1, 44, 30),(1, 44, 32),(1, 45, 33),(1, 45, 30),(1, 45, 32);              

-- 5 +/- SELECT COUNT(*) FROM PUBLIC.ORDERTABLE;               
INSERT INTO ORDERTABLE VALUES (46, 'En cours', 8),(47, 'En cours', 8),(48, 'En cours', 8),(49, 'En cours', 8),(50, 'En cours', 8);   


-- 5 +/- SELECT COUNT(*) FROM PUBLIC.ORDERDETAIL;              
INSERT INTO ORDERDETAIL VALUES (1, 36, 46),(1, 34, 47),(1, 40, 48),(1, 39, 49),(1, 43, 50);             



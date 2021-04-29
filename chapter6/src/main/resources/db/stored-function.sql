CREATE FUNCTION getFirstNameById(in_id INT)
 RETURNS VARCHAR(60)
 declare 
 	firstName varchar(60);
 BEGIN
 RETURN SELECT first_name into firstName FROM singer WHERE id = in_id);
	return firstName;
 END 
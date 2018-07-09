CREATE TABLE IF NOT EXISTS public.item
	(
	  id serial primary key,
	  name varchar(8000),
	  description varchar(8000),
	  creationdate timestamp
	);
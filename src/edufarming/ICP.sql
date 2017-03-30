create database ICP;
use ICP;

 create table project(
name varchar(50) unique,
quantity int,
price double,
date date);

create table sign_up(
firstname varchar(50),
lastname varchar(50),
username varchar(50),
Date_of_Birth date,
Farming_Status enum('Experienced Farmer', 'New To Farming'),
password varchar(50),
confirm_password varchar(50),
email varchar(100));

create table farmers_Info(
title varchar(60) not null,
information longtext not null
);

create table anim_farming (
title varchar(100) not null,
information longtext not null
);


insert into farmers_Info values(title,information),
('Farming for Beginners', 'Farming is the growing of crops and raising cattle. 
As a new faremr, you should first learn the basics modes of farming techniques 
and also given information on the required type of cattle to raise. As a farmer, 
you should also be able to set goals and set out a plan on how to achieve these 
goals because customers will be placing orders and their deadlines that you ought to satisfy.'),
('Plant Rotation Method', 'Rotating the plants (crops) on your farm enhances plant health and soil quality. 
To rotate your plantings, divide your garden into several sections and plant each section with a different family
 of plant. Next year, plant something from the next family. For instance, plant squash in section one the first
 year; the next year, plant peas there; next year, plant tomatoes; and well, you get the idea.'
);

insert into anim_farming values(title, information),
('Animal Farming', 'What’s a farm without animals? A farmer who wants to raise animals must understand that he or she is responsible for their care. Animals need food, water, exercise and clean shelter everyday. Good hygiene and care can deter health problems. This is the minimum care you should give your farm animals:
•	Provide clean (and unfrozen) water daily.
•	Provide sufficient food (each animal has different diet requirements).
•	Keep the living area clean.
•	Provide proper grooming (each animal has different needs)
•	Provide exercise or the opportunity for the animal to just get out and run.
•	If animals are herd animals (such as alpacas), be sure to have at least two.
•	Interact with your animals regularly not only so they get used to your being in the pen but also so bonds can form.'),
('Care for your Animals', 'A farmer, must keep an eye on the health of the farm animals by checking them routinely. Daily observation tells you the animals’ habits, and will help you determine if something is wrong or if your animal is sick.
The following signs are warnings that your farm animal has an illness:
•	The animal is lethargic or just not very active.
•	The animal isn’t eating.
•	The animal is getting thin.
•	The animal’s milk production is off.
•	There’s a change in the animal’s stools (you notice diarrhea or straining and a lack of stools, indicating constipation).'
);






select * from sign_up;
select * from project;
select * from farmers_Info;



create database foodbox;
use foodbox;
select * from admin;
insert into admin value("admin","admin");

select * from admin;
select * from cart;
select * from customer;
select * from product;

insert into Product values(124624,155,"yes","Veg","This Veg Khabab is resturant special",0,"./assets/images/Veg Khabab.jfif","Veg Kebab",155);
insert into Product values(124625,200,"yes","NonVeg","Chicken Manchurian with Thick wet gravy",25,"./assets/images/Chicken manchurian.jfif","Chicken Manchurian",150);
insert into Product values(124627,450,"yes","NonVeg","One of the famous mutton Recipie",0,"./assets/images/Mutton Kebab.jfif","Mutton Kebab",450);
insert into Product values(124629,300,"yes","NonVeg","Its dry mutton with extra black pepper",0,"./assets/images/Mutton Pepper fry.jfif","Mutton Pepper Fry",300);

insert into Product values(134561,150,"yes","Veg","A perfectly savory, tangy, spiced mixture made of onions and scrambled paneer",0,"./assets/images/Paneer-Bhurji.jpg","Paneer Bhurji",150);
insert into Product values(134562,50,"yes","Veg","It is made of soya flour and other healthy ingredients that are rich in vitamins, minerals.",0,"./assets/images/Roti.jpg","Roti",50);
insert into Product values(134563,35,"yes","Veg","Crispy & roasted style",0,"./assets/images/Dosa.jpg","Dosa",35);
insert into Product values(134564,180,"yes","Veg","It is made with soft tofu in a rich and spicy of sauce in vegetarian version.",5,"./assets/images/Kung Pao Tofu.jpg","Kung Pao Tofu",175);

insert into Product values(144626,250,"yes","Dessert","Icecream with vanila flavour",10,"./assets/images/Classic chocolate mousse.jfif","Classic chocolate mousse",225);
insert into Product values(144627,200,"yes","Dessert","Choclate lava cake with added sugers",0,"./assets/images/Choco Lava Cake.jfif","Choco Lava Cake",200);



select * from purchase;
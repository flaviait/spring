create table vehicles (
  id integer identity,
  manufacturer varchar(100) not null,
  model varchar(100) not null
);


create table rentals (
  id integer identity,
  vehicle_id integer not null,
  rental_date date not null,

  foreign key(vehicle_id) references vehicles(id),
  unique (vehicle_id, rental_date)
)

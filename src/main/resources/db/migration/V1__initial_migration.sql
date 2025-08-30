create table users(
    id serial primary key not null,
    username varchar(255) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null
);

create table roles(
    id serial primary key not null,
    name varchar(255) not null
);

create table user_roles(
    user_id int not null,
    role_id int not null,
    primary key(user_id, role_id),
    constraint fk_user foreign key (user_id) references users(id) on delete cascade,
    constraint fk_role foreign key (role_id) references roles(id) on delete cascade
);

create table departments(
    id serial primary key not null,
    name varchar(255) not null
);

create table job_titles(
    id serial primary key not null,
    name varchar(255) not null,
    department_id int not null,
    constraint fk_department foreign key (department_id) references departments(id) on delete cascade
);

create table shifts(
    id serial primary key not null,
    name varchar(255) not null,
    start_time time not null,
    end_time time not null,
    multiplier decimal(2, 2)
);

create table profiles(
    id serial primary key not null,
    duration int,
    hourly_rate decimal(100, 2),
    nfc varchar(255) unique,
    user_id int not null,
    job_id int not null,
    shift_id int not null,
    constraint fk_u_profile foreign key (user_id) references users(id) on delete cascade,
    constraint fk_j_profile foreign key (job_id) references job_titles(id) on delete cascade,
    constraint fk_s_profile foreign key (shift_id) references shifts(id) on delete cascade
);

create table attendance_records(
    id serial primary key not null,
    day_date date not null,
    time_in time not null,
    time_out time not null,
    status varchar(255) not null,
    user_id int not null,
    constraint fk_user_attendance foreign key (user_id) references users(id) on delete cascade
);




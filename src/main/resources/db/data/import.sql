set FOREIGN_KEY_CHECKS=0;

truncate table permission_year;
truncate table public_holiday;
truncate table permission_request;
truncate table department;
truncate table employee;


INSERT INTO department (id, name, manager_employee_id, created_at, updated_at, version ) VALUES (1, 'Bilgi İşlem', null, null, null, null);

INSERT INTO employee (id, last_name, name, start_hiring_date, departman_id, created_at, updated_at, version) VALUES (1, 'Sunal', 'Ahmet', '2012-01-15', 1, null, null, null);
INSERT INTO employee (id, last_name, name, start_hiring_date, departman_id, created_at, updated_at, version) VALUES (2, 'Akkus', 'Murat', '2017-02-15', 1, null, null, null);



update department set manager_employee_id=1 where id=1;






INSERT INTO permission_request (id, description, holiday_start_date, status, working_start_date, employee_id, created_at, updated_at, version, number_of_request_permission_day) VALUES (1, null, '2020-01-11', 'REQUESTED', '2020-01-14', 1, null, null, null, null);


INSERT INTO permission_year (id, end_year, number_of_day, start_year) VALUES (1, 5, 15, 0);
INSERT INTO permission_year (id, end_year, number_of_day, start_year) VALUES (2, 10, 18, 5);
INSERT INTO permission_year (id, end_year, number_of_day, start_year) VALUES (3, 99, 24, 10);

INSERT INTO public_holiday (id, date, name, created_at, updated_at, version) VALUES (1, '2020-04-23', '23 Nisan', null, null, null);
INSERT INTO public_holiday (id, date, name, created_at, updated_at, version) VALUES (2, '2020-05-01', '1 Mayıs', null, null, null);


set FOREIGN_KEY_CHECKS=1;
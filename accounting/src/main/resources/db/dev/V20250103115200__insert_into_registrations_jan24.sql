INSERT INTO registrations(
    registration_id, cashbook_id, description, amount, type_operation, date_operation, cost_center, supplier
)
VALUES
    ('017ca2ae-b6fe-4e87-bcfd-242573449f15', 'c111de6e-396c-4940-bd95-394af78076db', 'Ração', 196.80, 'OUTPUT', '2024-01-03', 'PET', 'Cobasi'),
    ('2c92948d-c9c2-4a6b-92ed-8218b257c032', 'c111de6e-396c-4940-bd95-394af78076db', 'Manutenção de conta bancária', 35.90, 'OUTPUT', '2024-01-03', 'BANKING', 'Itaú'),
    ('c7712b1f-fc55-4d33-a513-1b74ecdfbcfa', 'c111de6e-396c-4940-bd95-394af78076db', 'Pensão', 425.00, 'OUTPUT', '2024-01-08', 'PENSION', 'LH/V'),
    ('3b1465e2-94e0-4119-a2a6-08f4e4911bab', 'c111de6e-396c-4940-bd95-394af78076db', 'Assinatura de Zwift', 86.00, 'OUTPUT', '2024-01-08', 'SPORT', 'Zwift'),
    ('77545597-e16c-4dba-9490-bd90911c6985', 'c111de6e-396c-4940-bd95-394af78076db', 'Anualidade de domínio de site', 147.87, 'OUTPUT', '2024-01-08', 'SERVICES', 'Hostgator'),
    ('a46206b3-c341-4fe6-9be1-f8d1933f8ff5', 'c111de6e-396c-4940-bd95-394af78076db', 'Financiamento Jornalismo', 206.78, 'OUTPUT', '2024-01-08', 'EDUCATION', 'Caixa Econômica'),
    ('003e60aa-6374-4936-b802-25949dd70aa9', 'c111de6e-396c-4940-bd95-394af78076db', 'Seguro desemprego', 2231.00, 'INPUT', '2024-01-08', 'WAGE', 'Governo Federal'),
    ('a8c682a1-21b8-43c3-abf2-bf94ecd1e74d', 'c111de6e-396c-4940-bd95-394af78076db', 'Ração', 77.90, 'OUTPUT', '2024-01-09', 'PET', 'Cobasi'),
    ('a42d9646-888c-4f25-93df-1cca79bd0f7b', 'c111de6e-396c-4940-bd95-394af78076db', 'Mensalidade Pós-graduação', 282.45, 'OUTPUT', '2024-01-10', 'EDUCATION', 'Vincit'),
    ('85c2f3cf-18ed-45d7-9f82-4e0612150575', 'c111de6e-396c-4940-bd95-394af78076db', 'Celular', 20.00, 'OUTPUT', '2024-01-10', 'TELEPHONY', 'Tim'),
    ('40c6e346-a8fd-4f92-919b-c3b32b21f9db', 'c111de6e-396c-4940-bd95-394af78076db', 'Curso de programação', 94.90, 'OUTPUT', '2024-01-15', 'EDUCATION', 'Udemy'),
    ('0f3b876d-db9f-4828-82b6-ee3bf9681a99', 'c111de6e-396c-4940-bd95-394af78076db', 'Cursos', 55.80, 'OUTPUT', '2024-01-18', 'EDUCATION', 'Udemy'),
    ('4a8b3d7c-a077-4cbd-9f6f-62f896fbaa46', 'c111de6e-396c-4940-bd95-394af78076db', 'Armazenamento em Nuvem', 280.00, 'OUTPUT', '2024-01-22', 'SERVICES', 'Evernote'),
    ('cb85d8cd-2e6c-47f3-82d3-61f6d3dacd0e', 'c111de6e-396c-4940-bd95-394af78076db', 'Livro', 51.77, 'OUTPUT', '2024-01-22', 'EDUCATION', 'Amazon'),
    ('c3303a61-1e59-439f-aea8-75e13c94aa1c', 'c111de6e-396c-4940-bd95-394af78076db', 'Livros', 61.82, 'OUTPUT', '2024-01-22', 'EDUCATION', 'Amazon'),
    ('733529d1-10b5-4863-9392-68a271a72ceb', 'c111de6e-396c-4940-bd95-394af78076db', 'Ossos e petiscos', 98.59, 'OUTPUT', '2024-01-22', 'PET', 'Cobasi'),
    ('95eacee7-8767-485f-a5db-47136ca0296d', 'c111de6e-396c-4940-bd95-394af78076db', 'Curso', 27.90, 'OUTPUT', '2024-01-24', 'EDUCATION', 'Udemy'),
    ('ee2d7a3b-5a78-466f-8cca-df3793a3dfcb', 'c111de6e-396c-4940-bd95-394af78076db', 'Celular', 50.00, 'OUTPUT', '2024-01-25', 'TELEPHONY', 'Tim'),
    ('e627c26c-eca4-47b1-bccb-fc445744e0ab', 'c111de6e-396c-4940-bd95-394af78076db', 'Exame admissional', 30.00, 'OUTPUT', '2024-01-26', 'SERVICES', 'Siga'),
    ('e4a14387-bada-4313-9ea0-08550ccaccf9', 'c111de6e-396c-4940-bd95-394af78076db', 'Transporte', 12.84, 'OUTPUT', '2024-01-26', 'TRANSPORT', 'Uber'),
    ('462cd1f1-4584-448b-9215-f26f137c9a44', 'c111de6e-396c-4940-bd95-394af78076db', 'Corte de cabelo', 40.00, 'OUTPUT', '2024-01-26', 'BARBERSHOP', 'Careca'),
    ('afacd0d1-5c69-4e5c-b57e-ac67f5ada918', 'c111de6e-396c-4940-bd95-394af78076db', 'Livro', 36.66, 'OUTPUT', '2024-01-30', 'EDUCATION', 'Amazon'),
    ('39554cd9-7070-4194-ab23-b079ea9b5788', 'c111de6e-396c-4940-bd95-394af78076db', 'Curso', 27.90, 'OUTPUT', '2024-01-30', 'EDUCATION', 'Udemy'),
    ('8145b18e-5f34-4a97-aec1-21a11247003d', 'c111de6e-396c-4940-bd95-394af78076db', 'Curso', 27.90, 'OUTPUT', '2024-01-30', 'EDUCATION', 'Udemy');


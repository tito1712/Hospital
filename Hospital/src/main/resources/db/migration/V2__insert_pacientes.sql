INSERT INTO paciente (nombre, apellido, correo, telefono, direccion, fecha_nacimiento)
SELECT * FROM (
    SELECT 'Carlos'    as nombre, 'Martinez'  as apellido, 'carlos.martinez@gmail.com'   as correo, '55501001' as telefono, 'Av. Central 101'      as direccion, '1985-03-12' as fecha_nacimiento UNION ALL
    SELECT 'Ana',                 'Lopez',                 'ana.lopez@gmail.com',                   '55501002',              'Calle 5 #22',                       '1990-07-24'                      UNION ALL
    SELECT 'Luis',                'Garcia',                'luis.garcia@gmail.com',                 '55501003',              'Blvd. Norte 300',                   '1978-11-05'                      UNION ALL
    SELECT 'Maria',               'Hernandez',             'maria.hernandez@gmail.com',             '55501004',              'Col. Centro 44',                    '1995-01-18'                      UNION ALL
    SELECT 'Jorge',               'Perez',                 'jorge.perez@gmail.com',                 '55501005',              'Calle 8 #15',                       '1982-06-30'                      UNION ALL
    SELECT 'Laura',               'Sanchez',               'laura.sanchez@gmail.com',               '55501006',              'Av. Sur 210',                       '1993-09-14'                      UNION ALL
    SELECT 'Miguel',              'Ramirez',               'miguel.ramirez@gmail.com',              '55501007',              'Calle 12 #88',                      '1975-04-22'                      UNION ALL
    SELECT 'Sofia',               'Torres',                'sofia.torres@gmail.com',                '55501008',              'Col. Las Flores 9',                 '1998-12-03'                      UNION ALL
    SELECT 'Roberto',             'Flores',                'roberto.flores@gmail.com',              '55501009',              'Av. Oriente 77',                    '1988-08-16'                      UNION ALL
    SELECT 'Valentina',           'Diaz',                  'valentina.diaz@gmail.com',              '55501010',              'Blvd. Reforma 55',                  '1996-02-27'                      UNION ALL
    SELECT 'Fernando',            'Morales',               'fernando.morales@gmail.com',            '55501011',              'Calle 3 #40',                       '1980-05-09'                      UNION ALL
    SELECT 'Isabella',            'Jimenez',               'isabella.jimenez@gmail.com',            '55501012',              'Av. Juarez 18',                     '1992-10-21'                      UNION ALL
    SELECT 'Andres',              'Ruiz',                  'andres.ruiz@gmail.com',                 '55501013',              'Col. Moderna 66',                   '1987-07-07'                      UNION ALL
    SELECT 'Camila',              'Vargas',                'camila.vargas@gmail.com',               '55501014',              'Calle 20 #5',                       '1994-03-31'                      UNION ALL
    SELECT 'Diego',               'Castro',                'diego.castro@gmail.com',                '55501015',              'Av. Independencia 90',              '1983-01-15'                      UNION ALL
    SELECT 'Lucia',               'Ortiz',                 'lucia.ortiz@gmail.com',                 '55501016',              'Blvd. Sur 33',                      '1997-06-08'                      UNION ALL
    SELECT 'Pablo',               'Mendoza',               'pablo.mendoza@gmail.com',               '55501017',              'Calle 7 #12',                       '1976-09-19'                      UNION ALL
    SELECT 'Gabriela',            'Cruz',                  'gabriela.cruz@gmail.com',               '55501018',              'Col. Jardines 4',                   '1991-11-28'                      UNION ALL
    SELECT 'Sebastian',           'Reyes',                 'sebastian.reyes@gmail.com',             '55501019',              'Av. Libertad 67',                   '1986-04-04'                      UNION ALL
    SELECT 'Daniela',             'Moreno',                'daniela.moreno@gmail.com',              '55501020',              'Calle 15 #99',                      '1999-08-11'
) WHERE NOT EXISTS (SELECT 1 FROM paciente);

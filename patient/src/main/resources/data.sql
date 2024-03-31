-- // insert into
INSERT INTO `patient` (`id`, `address`, `birthdate`, `firstname`, `gender`, `lastname`, `phone`) VALUES
    (2, '1 Brookside St', '1968-12-07 00:00:00.000000', 'Test', 'F', 'TestNone', '100-222-3333'),
    (3, '2 High St', '1945-06-24 00:00:00.000000', 'Test', 'M', 'TestBorderline', '200-333-4444'),
    (4, '3 Club Road', '2004-06-18 00:00:00.000000', 'Test', 'M', 'TestInDanger', '300-444-5555'),
    (5, '4 Valley Dr', '2002-06-28 00:00:00.000000', 'Test', 'F', 'TestEarlyOnset', '400-555-6666');
COMMIT;
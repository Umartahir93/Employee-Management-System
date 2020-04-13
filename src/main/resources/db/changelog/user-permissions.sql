GRANT USAGE ON SCHEMA accounting_${env} TO dbuser_${env};
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA accounting_${env} TO dbuser_${env};
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA accounting_${env} TO dbuser_${env};
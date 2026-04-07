create table cliente(
    id bigserial primary key unique not null,
    nome text not null,
    cpf text unique not null,
    telefone text not null
);
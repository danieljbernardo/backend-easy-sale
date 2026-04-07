create table usuario(
    id bigserial primary key unique not null,
    nome text not null,
    cpf text unique not null,
    email text unique not null,
    senha text not null,
    role text not null
);
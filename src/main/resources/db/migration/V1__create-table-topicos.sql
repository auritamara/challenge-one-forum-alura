create table cursos
(
    id        bigint       not null auto_increment,
    categoria varchar(255) not null,
    nome      varchar(255) not null,
    primary key (id),
    unique index `nome_UNIQUE` (nome ASC) visible
) engine = InnoDB
  default char set = utf8mb4
  collate = utf8mb4_0900_ai_ci;

create table usuarios
(
    id    bigint auto_increment,
    email varchar(255) not null,
    nome  varchar(255) not null,
    senha varchar(255) not null,
    primary key (id),
    unique index `email_UNIQUE` (email ASC) visible
) engine = InnoDB
  default char set = utf8mb4
  collate = utf8mb4_0900_ai_ci;

create table topicos
(
    id           bigint       not null auto_increment,
    data_criacao timestamp(6) not null,
    mensagem     varchar(255) not null,
    status       varchar(255) not null,
    titulo       varchar(255) not null,
    autor        bigint       not null,
    curso        bigint       not null,
    primary key (id),
    unique index `titulo_UNIQUE` (titulo ASC) visible,
    unique index `mensagem_UNIQUE` (mensagem ASC) visible,
    constraint `fk_t_curso_id`
        foreign key (curso)
            references cursos (id),
    constraint `fk_t_autor_id`
        foreign key (autor)
            references usuarios (id)
) engine = InnoDB
  default char set = utf8mb4
  collate = utf8mb4_0900_ai_ci;

create table respostas
(
    id           bigint       not null auto_increment,
    data_criacao timestamp(6) not null,
    mensagem     varchar(255) not null,
    solucao      boolean      not null,
    autor        bigint       not null,
    topico       bigint       not null,
    primary key (id),
    constraint `fk_r_topico_id`
        foreign key (topico)
            references topicos (id),
    constraint `fk_r_autor_id`
        foreign key (autor)
            references usuarios (id)
) engine = InnoDB
  default char set = utf8mb4
  collate = utf8mb4_0900_ai_ci;
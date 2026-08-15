--liquibase formatted sql

--changeset yourname:init_vector_store dbms:postgresql splitStatements:true endDelimiter:; runAlways:true
DROP TABLE IF EXISTS public.vector_store;

CREATE TABLE public.vector_store (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    content text UNIQUE,
    metadata jsonb,
    embedding vector(1024)
);

--changeset yourname:init_chat_memory dbms:postgresql splitStatements:true endDelimiter:; runAlways:true
DROP TABLE IF EXISTS public.spring_ai_chat_memory;

CREATE TABLE public.spring_ai_chat_memory (
    conversation_id varchar(255) NOT NULL,
    content jsonb NOT NULL,
    type varchar(255) NOT NULL,
    "timestamp" bigint NOT NULL,
    PRIMARY KEY (conversation_id, "timestamp")
);

CREATE INDEX idx_chat_memory_conversation_id ON public.spring_ai_chat_memory (conversation_id);
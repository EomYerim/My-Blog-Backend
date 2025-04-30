package com.my.blog.adapter.in.web;

public record PostRequest (
	String title,
	String content,
	String author
){}

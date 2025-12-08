package com.example.booking_tour.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SpaRedirectFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        // 1. Backend API requests -> Let them pass
        if (path.startsWith("/api") || path.startsWith("/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Static resources (files with extensions) -> Let them pass
        // Simple check: checks if there's a dot in the last part of the path
        if (hasExtension(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. For everything else (SPA paths like /login, /dashboard) -> Forward to
        // index.html
        // The ResourceHandler will then serve index.html
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    private boolean hasExtension(String path) {
        // Exclude root "/" which refers to index but handled separately or via welcome
        // page
        if (path.equals("/"))
            return false;

        int lastDotIndex = path.lastIndexOf('.');
        int lastSlashIndex = path.lastIndexOf('/');
        // If there is a dot AFTER the last slash, it's likely a file
        return lastDotIndex > lastSlashIndex;
    }
}

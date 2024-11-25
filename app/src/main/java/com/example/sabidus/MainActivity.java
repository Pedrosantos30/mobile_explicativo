package com.example.sabidus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa a WebView buscando pelo ID no layout
        webview = findViewById(R.id.webview);

        // Define um WebViewClient para carregar as URLs dentro da WebView
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // Exibe um log ou uma mensagem sempre que a página começa a carregar
                Toast.makeText(MainActivity.this, "Carregando: " + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Exibe uma mensagem ou log quando a página for carregada completamente
                Toast.makeText(MainActivity.this, "Página carregada: " + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Exibe um erro se houver problemas ao carregar a página
                Toast.makeText(MainActivity.this, "Erro ao carregar: " + description, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, android.webkit.WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                // Exibe uma mensagem caso ocorra um erro HTTP
                Toast.makeText(MainActivity.this, "Erro HTTP ao carregar: " + request.getUrl(), Toast.LENGTH_LONG).show();
            }
        });

        // Habilita o JavaScript para o carregamento das páginas
        webview.getSettings().setJavaScriptEnabled(true);
        // Permite o carregamento de conteúdo dentro da WebView
        webview.getSettings().setAllowContentAccess(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // Verifica se há um estado salvo (quando a atividade é recriada)
        if (savedInstanceState == null) {
            // Carrega a URL inicial
            webview.loadUrl("https://ranielluna.github.io/Sabidus/");
        } else {
            // Se houver um estado salvo, restaura o estado da WebView
            webview.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salva o estado da WebView para que a navegação não seja perdida
        webview.saveState(outState);
    }
}

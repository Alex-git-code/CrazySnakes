package org.alexcode.crazysnakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameMenu extends AppCompatActivity {
    private Button startGame, viewProfile, viewStats, quitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);
        initGameMenu();
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameMenu.this, "Game started", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GameMenu.this, Gameplay.class);
                startActivity(intent);
            }
        });
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameMenu.this, "Show profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GameMenu.this, PlayerProfile.class);
                startActivity(intent);
            }
        });
        viewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameMenu.this, "Show stats", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GameMenu.this, PlayerStats.class);
                startActivity(intent);
            }
        });

        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameMenu.this, "Quit", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
    }

    private void initGameMenu() {
        startGame = findViewById(R.id.startGame);
        viewProfile = findViewById(R.id.userProfile);
        viewStats = findViewById(R.id.userStats);
        quitGame = findViewById(R.id.quirGame);
    }

    //TODO: add admin access
}
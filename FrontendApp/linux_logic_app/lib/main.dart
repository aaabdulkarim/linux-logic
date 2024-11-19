import 'package:flutter/material.dart';

void main() {
  runApp(LinuxLogicApp());
}

class LinuxLogicApp extends StatelessWidget {
  const LinuxLogicApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: MainPage(),
    );
  }
}

class MainPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Linux Logic'),
        actions: [
          IconButton(
            icon: const Icon(Icons.settings),
            onPressed: () {
              // Öffnet den Slider (Drawer als Beispiel)
              Scaffold.of(context).openEndDrawer();
            },
          ),
        ],
      ),
      endDrawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            const DrawerHeader(
              decoration: BoxDecoration(color: Colors.teal),
              child: Text('Einstellungen', style: TextStyle(color: Colors.white)),
            ),
            ListTile(
              title: const Text('Allgemein'),
              onTap: () {
                // Aktionen bei Auswahl
              },
            ),
            ListTile(
              title: const Text('Sprache'),
              onTap: () {},
            ),
            ListTile(
              title: const Text('Konto'),
              onTap: () {},
            ),
            // Weitere Menüpunkte ...
          ],
        ),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              const Center(
                child: Text(
                  'Linux Logic',
                  style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
                ),
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () {
                  // Weiter Spielen Aktion
                },
                child: const Text('Weiter Spielen'),
              ),
              const SizedBox(height: 32),
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.teal.shade100,
                  borderRadius: BorderRadius.circular(8),
                ),
                child: const Text(
                  'Diplomprojekt Linux Logic\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  textAlign: TextAlign.center,
                ),
              ),
              const SizedBox(height: 32),
              Center(
                child: Column(
                  children: [
                    const Text('Try Out', style: TextStyle(fontSize: 20)),
                    const SizedBox(height: 16),
                    Container(
                      height: 150,
                      width: double.infinity,
                      color: Colors.grey.shade300,
                      child: const Center(
                        child: Text('Demo-Content'),
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Linux Logic',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.play_arrow),
            label: 'Weitere Spielen',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.book),
            label: 'Kurse',
          ),
        ],
        onTap: (index) {
          // Navigation je nach Tab
        },
      ),
    );
  }
}

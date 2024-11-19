import 'package:flutter/material.dart';

void main() {
  runApp(const LoginPage());
}

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        body: Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/linux_logic_login.png"),
              fit: BoxFit.fill,
            ),
          ),
        ),
        bottomNavigationBar: Footer(),
      ),
    );
  }
}

class Footer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xff559191), // Hintergrundfarbe für den Footer
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 40),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          // Webseitenlink
          GestureDetector(
            onTap: () {

            },
            child: const Text(
              'www.linux-logic.com',
              style: TextStyle(
                color: Colors.white, // Linkfarbe
                decoration: TextDecoration.underline, // Unterstreichung für Link
              ),
            ),
          ),
          // Rechtsbündiger Text
          const Text(
            'TGM 2024/25',
            style: TextStyle(
              fontFamily: 'UbuntuMono',
              color: Colors.white,
              fontSize: 16,
              fontWeight: FontWeight.bold,
            ),
          ),
        ],
      ),
    );
  }
}
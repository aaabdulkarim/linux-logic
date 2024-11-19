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
              image: AssetImage("images/linux_logic_login.png"),
              fit: BoxFit.cover,
            ),
          ),
          child: const Center(
            child: Text("Hi"),
          ),
        ),
      ),
    );
  }
}
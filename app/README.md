# 🏎️ F1 AI Companion

F1 AI Companion е Android мобилно приложение, което използва изкуствен интелект (Google Gemini API), за да предоставя анализи, прогнози и информация, свързана с Формула 1.

Приложението е разработено с Kotlin и Jetpack Compose, следвайки MVVM архитектура.

---

# 🚀 Функционалности

## 💬 AI Chat
- Потребителят може да задава въпроси, свързани с Формула 1
- AI дава анализи за състезания, пилоти и стратегии

## 🏁 Race Predictor
- Генерира Top 3 прогнозa за избрано състезание
- Дава обяснение базирано на:
    - форма на пилотите
    - представяне на отборите
    - характеристики на пистата

## 🧠 AI анализ
- Използва Google Gemini API
- Специализирани prompt-и за F1 контекст

---

# 🛠️ Технологии

- Kotlin
- Jetpack Compose
- MVVM архитектура
- OkHttp (HTTP заявки)
- Google Gemini API
- JSON обработка

---

# 🧱 Архитектура

Приложението е структурирано по MVVM модел:

- **UI слой (Compose)**
    - ChatScreen
    - RacePredictorScreen

- **ViewModel слой**
    - ChatViewModel
    - RacePredictorViewModel
    - Управление на state и логика

- **Data слой**
    - GeminiService (API комуникация)

---

# 🤖 AI интеграция

Приложението използва Google Gemini API за генериране на отговори.

## Примерен prompt:
You are a professional Formula 1 analyst.
Predict the top 3 finishers for the race and explain your reasoning.


---

# 📱 Екрани

- 💬 Chat Screen – AI чат за F1 въпроси
- 🏁 Race Predictor – AI прогнози за състезания

---

# ⚙️ Стартиране на проекта

1. Клониране на проекта
2. Добавяне на Gemini API key
3. Отваряне в Android Studio
4. Стартиране на emulator или реално устройство

---

# 🔐 API Key

API ключът НЕ трябва да бъде качван в GitHub.

Препоръчва се използване на:
- local.properties
- BuildConfig

---

# 🎯 Цел на проекта

Целта на проекта е да демонстрира:

- интеграция на изкуствен интелект в мобилно приложение
- работа с REST API
- MVVM архитектура
- UI дизайн с Jetpack Compose
- prompt engineering за специфичен домейн (Formula 1)

---

# 🏆 Заключение

F1 AI Companion комбинира модерни Android технологии и изкуствен интелект, за да предостави интерактивно и интелигентно потребителско изживяване, фокусирано върху Формула 1.
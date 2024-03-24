# Cognitive Services and The optical character recognition (OCR) Analytics

**Objective**: Purpose of the assignment is to integrate Machine Learning and AI as part of the Web UI. 

**OCR service used**: Amazon Textract 

**Steps involved:**
1. Give the app permissions so that it can access the images present on mobile and permission to access the internet.
2. Get accessId and secret key for AWS IAM user. Make sure the IAM user in AWS has appropriate permission to access Textract API.
3. Use Amazon Textract android SDK and create a client to detect the text in the image selected.
4. Display the response of the detectDocumentText() API in the UI.

**Testing:** If possible, test the app on an android device. You might come across some errors while testing on emulator because of different environment settings.

# Screenshots:
Main Activity:

<img width="181" alt="image" src="https://github.com/avidhi2100/CMPE-277-Assignments/assets/143249088/2a1b4e9c-5258-4130-a95e-dd94afc4bd79">

Choose image from folder:

<img width="181" alt="image" src="https://github.com/avidhi2100/CMPE-277-Assignments/assets/143249088/6ce6d892-827f-4dbd-8b5f-8d8084e0a558">

Example of Performing OCR on two different images:

<img width="476" alt="image" src="https://github.com/avidhi2100/CMPE-277-Assignments/assets/143249088/e820e9d4-300b-4cc5-a8db-98808183c5c5">

<img width="476" alt="image" src="https://github.com/avidhi2100/CMPE-277-Assignments/assets/143249088/a66adcec-601f-47d9-8cf9-8e120a1fe046">


**Image References:**

https://images.app.goo.gl/YUzHogVnCzvtJscq6

https://images.app.goo.gl/kLDtV6U1V45gkK2k9



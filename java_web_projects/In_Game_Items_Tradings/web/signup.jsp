<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/styleForm.css">
        <!-- formValidate JS -->
        <script src="UI/js/formValidate.js"></script>
    </head>

    <body>
        <div class="container nopadding">
            <!-- Sign Up Form -->
            <div class="registration form">
                <!-- Screen Header -->
                <header>Signup</header>
                <!-- Display Signup Error -->
                <div class="form-message">${signupFailed}</div>
                <div class="form-message">${existedUsername}</div>
                <!-- Main Form -->
                <form id="signup-form" action="SignUpController" method="post">
                    <!-- Username -->
                    <div class="form-group">
                        <label for="username" class="form-label">Username</label>
                        <input id="username" name="username" type="text" placeholder="Ex: laamwwibu" class="form-control">
                        <span class="form-message"></span>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                        <label for="password" class="form-label">Password</label>
                        <input id="password" name="password" type="password" placeholder="Enter Password"
                               class="form-control">
                        <span class="form-message"></span>
                    </div>
                    <!-- Confirm Password -->
                    <div class="form-group">
                        <label for="password_confirmation" class="form-label">Confirm Password</label>
                        <input id="password_confirmation" name="password_confirmation"
                               placeholder="Confirm your password" type="password" class="form-control">
                        <span class="form-message"></span>
                    </div>
                    <input type="text" name="game_account_id" value="${gameAccount.getId()}" hidden>
                </form>

                <!-- Form Buttons -->
                <div class="row">
                    <!-- Scren Switch -->
                    <div class="col-lg-8">
                    </div>
                    <!-- Summit button -->
                    <div class="col-lg-4">
                        <div class="summit-button">
                            <button type="submit" form="signup-form">Signup</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                Validator({
                    form: '#signup-form',
                    formGroupSelector: '.form-group',
                    errorSelector: '.form-message',
                    rules: [
                        Validator.isRequired('#username', 'Please enter your username'),
                        Validator.minLength('#password', 6),
                        Validator.isRequired('#password_confirmation'),
                        Validator.isConfirmed('#password_confirmation', function () {
                            return document.querySelector('#signup-form #password').value;
                        }, 'Password not matching')
                    ]
                });
            });
        </script>
    </body>

</html>
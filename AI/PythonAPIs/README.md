# Sources I am getting based on

+ [Working with APIs in Python - Code in 10 Minutes](https://www.youtube.com/watch?v=hpc5jyVpUpw)

+ [Como CRIAR uma API com PYTHON [DO ZERO]](https://www.you'tube.com/watch?v=FBLAV1SbJFk)

---

## Working with APIs in Python - Code in 10 Minutes

+ learned how to consume an API to get data - check getting_data.py

---

## Como CRIAR uma API com PYTHON [DO ZERO]

+ created a simple API with Flask - check app.py

---

### Starting a virtual environment in Python

+ `python3 -m venv (name for the venv)` -  this will create the environment (a new folder)
+ to activate the environment:
    + Windows (PowerShell): `/venv/Scripts/Activate.ps1`
        + probably won't work at first, because running scripts are disabled: run `Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned` to enable
    + not Windows: `source (name)/bin/activate`
+ check if your terminal now contains the name of the venv right at the start of the line
+ virtual environments are created to isolate different application dependencies
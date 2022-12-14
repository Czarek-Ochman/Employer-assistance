import React, {FunctionComponent} from 'react';
import "./login.scss";
import {TextField} from "@mui/material";
import Button from "@mui/material/Button";

type Props = {};

export const Login: FunctionComponent<Props> = (props: Props) => {
    return (
        <div className="login-container">
            <div className="login-box">
                <div className="login-text"> Logowanie</div>
                <div className="item">
                    <TextField id="outlined-basic" label="Email" variant="outlined" />
                </div>
                <div className="item">
                    <TextField id="outlined-basic" label="Hasło" variant="outlined" />
                </div>
                <div>
                <button>Zaloguj</button>
            </div>
                <Button href={"/rejestracja"}>Rejestracja</Button>
                <Button href={"/"}>Wstecz</Button>
            </div>
        </div>
    );
};
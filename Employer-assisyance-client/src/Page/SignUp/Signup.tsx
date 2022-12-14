import React, {FunctionComponent} from 'react';
import "./signup.scss";
import {TextField} from "@mui/material";
import Button from "@mui/material/Button";

type Props = {};

export const Signup: FunctionComponent<Props> = (props: Props) => {
    return (
        <div className="signup-container">
            <div className="signup-box">
                <div className="signup-text"> Rejestracja</div>
                <div className="item">
                    <TextField id="outlined-basic" label="Email" variant="outlined" />
                </div>
                <div className="item">
                    <TextField id="outlined-basic" label="Hasło" variant="outlined" />
                </div>
                <div className="item">
                    <TextField id="outlined-basic" label="Powtórz Hasło" variant="outlined" />
                </div>
                <div>
                    <button>Zarejestruj</button>
                </div>
                <Button href={"/login"}>Logowanie</Button>
                <Button href={"/"}>Wstecz</Button>
            </div>
        </div>
    );
};
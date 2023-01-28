import {confirmAlert} from 'react-confirm-alert'; // Import
import {useNavigate} from 'react-router-dom';
import React, {FunctionComponent} from "react";

type Props = React.DetailedHTMLProps<React.InputHTMLAttributes<HTMLInputElement>,
    HTMLInputElement> & {
    title: string,
    message: string,
    firstLabel: string
    secondLabel: string
    onClickFirst: any
    onClickSecond: any
};

const options = {
    closeOnEscape: true,
    closeOnClickOutside: true,
    keyCodeForClose: [8, 32],
    willUnmount: () => {
    },
    afterClose: () => {
    },
    onClickOutside: () => {
    },
    onKeypress: () => {
    },
    onKeypressEscape: () => {
    },
    overlayClassName: "overlay-custom-class-name"
};


const AlertWithOption: FunctionComponent<Props> = ({title, message, onClickFirst, onClickSecond, firstLabel, secondLabel}) => {

    const showAlertFirst = () => {
        if (onClickFirst) {
            onClickFirst();
        }
    };

    const showAlertSecond = () => {
        if (onClickSecond) {
            onClickSecond();
        }
    };

    confirmAlert({
            title: title,
            message: message,
            buttons: [
                {
                    label: firstLabel,
                    onClick: showAlertFirst,
                },
                {
                    label: secondLabel,
                    onClick: showAlertSecond,
                }
            ],
        }
    );

    return (
        <div>
            <div>
                <button type="button" onClick={showAlertFirst}>{firstLabel}</button>
                <button type="button" onClick={showAlertSecond}>{secondLabel}</button>
            </div>
        </div>
    );
};
export default AlertWithOption;
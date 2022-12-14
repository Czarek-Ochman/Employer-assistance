import {FunctionComponent} from 'react';
import "./homepage.scss";
import Workers from '../../Component/Shared/Assets/workers.jpeg';
import MediaCard from "../../Component/Shared/MediaCard";
import SalaryCard from "../../Component/Shared/SalaryCard";
import PositionCard from "../../Component/Shared/PositionCard";
import {MainFooter} from "../../Component/MainFooter/MainFooter";

type Props = {};

export const Homepage: FunctionComponent<Props> = (props: Props) => {
    return (
        <div className="homepage">
            <div className="main-text"> Dzięki korzystaniu z  Employer Assistance możesz w łatwy sposób zarządzać pracownikami.</div>
       <div className="items">
           <div className="item">
               <MediaCard />
           </div>
           <div className="item">
               <SalaryCard />
           </div>
           <div className="item">
               <PositionCard />
           </div>
           <div className="item">
               <MediaCard />
           </div>
           <div className="item">
               <MediaCard />
           </div>
           <div className="item">
               <MediaCard />
           </div>
       </div>
           <div className="home-footer"><MainFooter /></div>
        </div>
    );
};
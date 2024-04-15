import '../WelcomeAnimation/anim.scss'


const WelcomeAnim = (props) => {

    const {showAnim} = props;

    return <>
    
    <div   className ="animContainer" style={showAnim ?
         {display: 'grid' }
         :
          {display: 'none'} }>
        <h2>Welcome To Energy Saving Game Questionnaire</h2>
    </div>

    </>
}
export default WelcomeAnim;